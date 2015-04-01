/*
 * validate.js 1.4.1
 * Copyright (c) 2011 - 2014 Rick Harrison, http://rickharrison.me
 * validate.js is open sourced under the MIT license.
 * Portions of validate.js are inspired by CodeIgniter.
 * http://rickharrison.github.com/validate.js
 */

(function(window, document, undefined) {
    /*
     * If you would like an application-wide config, change these defaults.
     * Otherwise, use the setMessage() function to configure form specific messages.
     */

    var defaults = {
        messages: {
            required: '%s 不能为空',
            matches: '%s 与 %s 不匹配',
            "default": '请选择 %s',
            valid_email: '%s 不是合法的Email格式',
            valid_emails: '%s 必须全部为合法的Email格式',
            min_length: '%s 长度必须大于等于 %s',
            max_length: '%s 长度必须小于等于 %s',
            exact_length: '%s 长度必须等于 %s',
            greater_than: '%s 必须大于 %s',
            less_than: '%s 必须小于 %s',
            alpha: '%s 只能包含字母',
            alpha_numeric: '%s 只能包含字母和数字',
            alpha_dash: '%s 只能包含字母、数字、下划线和破折号',
            no_blank : '%s 不能有空白字符',
            numeric: '%s 只能包含数字',
            integer: '%s 只能包含整数',
            decimal: '%s 只能包含十进制数',
            is_natural: '%s 只能包含自然数0-9',
            is_natural_no_zero: '%s 只能包含自然数1-9',
            valid_ip: '%s 必须包含合法的IP地址',
            valid_base64: '%s 必须包含合法的base64字符',
            valid_credit_card: '%s 必须包含合法的银行卡号',
            is_file_type: '%s 必须只能为 %s 格式的文件',
            valid_url: '%s 必须包含合法的URL地址',
            valid_phone : '%s 格式不合法',
            valid_tel_phone : '%s 格式不合法',
            valid_identity : '身份证号不正确'
        },
        callback: function(errors) {

        }
    };

    /*
     * Define the regular expressions that will be used
     */

    var ruleRegex = /^(.+?)\[(.+)\]$/,
        numericRegex = /^[0-9]+$/,
        integerRegex = /^\-?[0-9]+$/,
        decimalRegex = /^\-?[0-9]*\.?[0-9]+$/,
        emailRegex = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/,
        alphaRegex = /^[a-z]+$/i,
        hasBlankRegex = /\s/i,
        alphaNumericRegex = /^[a-z0-9]+$/i,
        alphaDashRegex = /^[a-z0-9_\-]+$/i,
        naturalRegex = /^[0-9]+$/i,
        naturalNoZeroRegex = /^[1-9][0-9]*$/i,
        ipRegex = /^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/i,
        base64Regex = /[^a-zA-Z0-9\/\+=]/i,
        numericDashRegex = /^[\d\-\s]+$/,
        urlRegex = /^((http|https):\/\/(\w+:{0,1}\w*@)?(\S+)|)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?$/,
        idRegex = /^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/,
        phoneRegex = /^((1\d{10})|(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
        telPhoneRegex = /^1\d{10}$/;

    /*
     * The exposed public object to validate a form:
     *
     * @param formNameOrNode - String - The name attribute of the form (i.e. <form name="myForm"></form>) or node of the form element
     * @param fields - Array - [{
     *     name: The name of the element (i.e. <input name="myField" />)
     *     display: 'Field Name'
     *     rules: required|matches[password_confirm]
     * }]
     * @param callback - Function - The callback after validation has been performed.
     *     @argument errors - An array of validation errors
     *     @argument event - The javascript event
     */

    var FormValidator = function(formNameOrNode, fields, opts) {
        this.fail = opts.fail || defaults.callback;
        this.success = opts.success || defaults.callback;
        this.autoSubmit = opts.autoSubmit === undefined ? true : opts.autoSubmit;
        this.showError = opts.showError === undefined ? true : opts.showError;

        this.errors = [];
        this.fields = {};
        this.form = this._formByNameOrNode(formNameOrNode) || {};
        this.messages = {};
        this.handlers = {};
        this.conditionals = {};

        for (var i = 0, fieldLength = fields.length; i < fieldLength; i++) {
            var field = fields[i];
            this.addField(field); 
        }

        /*
         * Attach an event callback for the form submission
         */
        var _onsubmit = this.form.onsubmit;

        this.form.onsubmit = (function(that) {

            var preventDefault = function(evt){
                if (evt && evt.preventDefault) {
                    evt.preventDefault();
                } else if (event) {
                    // IE uses the global event variable
                    event.returnValue = false;
                }
            };

            return function(evt) {
                try {
                    that.hideError();
                    if(that._validateForm()){
                        if(_onsubmit !== null && _onsubmit !== undefined){
                            _onsubmit();
                        }
                        var flag = that.success(that.formToJson(),evt);

                        if(!that.autoSubmit || !flag){
                            preventDefault(evt); 
                        }
                    }else{

                        preventDefault(evt);
                        that.fail(that.errors,evt);
                        if(that.showError){
                            that.displayError(that.errors);                        
                        }
                    }
                } catch(e) {
                    console.log(e); 
                }
            };
        })(this);
    },

    attributeValue = function (element, attributeName) {
        var i;

        if ((element.length > 0) && (element[0].type === 'radio' || element[0].type === 'checkbox')) {
            for (i = 0, elementLength = element.length; i < elementLength; i++) {
                if (element[i].checked) {
                    return element[i][attributeName];
                }
            }

            return;
        }

        return element[attributeName];
    };


    /*
     * @public
     * Sets a custom message for one of the rules
     */

    FormValidator.prototype.setMessage = function(rule, message) {
        this.messages[rule] = message;

        // return this for chaining
        return this;
    };

    FormValidator.prototype.addField = function(field){
        // If passed in incorrectly, we need to skip the field.
        if (!field.name && !field.names) {
            return;
        }

        /*
         * Build the master fields array that has all the information needed to validate
         */

        if (field.names) {
            for (var j = 0, fieldNamesLength = field.names.length; j < fieldNamesLength; j++) {
                this._addField(field, field.names[j]);
            }
        } else {
            this._addField(field, field.name);
        }
    };

    FormValidator.prototype.deleteField = function(fieldName){
        this._deleteField(fieldName);
    };

    FormValidator.prototype.displayError = function(errors){
        var hideToolTip = function(){
                $(this).tooltip('hide');
            };

        for(var i = 0,len = errors.length; i < len; i ++){
            $(errors[i].element)
                .off('blur',hideToolTip)
                .tooltip('destroy')
                .tooltip({
                    html : true,
                    title : errors[i].message,
                    trigger : "manual",
                    placement : "right",
                    //bgColor : "#efefef",
                    //textColor : "red",
                    customStyle : {
                        '.tooltip-inner' : {
                            'background-color' : '#efefef',
                            'color' : 'red'
                        },
                        '.tooltip-arrow' : {
                            'border-right-color' : '#efefef'
                        }
                    },
                    container : this.fields[errors[i].name].tipsTarget,
                    posTarget : this.fields[errors[i].name].posTarget
                })
                .tooltip('show')
                .one('blur',hideToolTip);
        }
    };

    FormValidator.prototype.hideError = (function(){
        var _hide = function(field){
            $(field.element).tooltip("destroy");    
        };

        return function(fields){
            if(typeof fields === "undefined"){
                fields = this.fields;  

                for(var i in fields){
                    _hide(fields[i]);
                }
            }else if(typeof fields === 'string'){
                _hide(this.fields[fields]);
            }else if($.isArray(fields)){
                for(var i = 0,len = fields.length; i < len; i ++){
                    _hide(this.fields[fields[i]]);
                }
            }
        };
    })();

    FormValidator.prototype.formToJson = function(){
        var ret = {};
        for(var key in this.fields){
            ret[key] = this.fields[key]['value']; 
        }
        return ret;
    };

    /*
     * @public
     * Registers a callback for a custom rule (i.e. callback_username_check)
     */

    FormValidator.prototype.registerCallback = function(name, handler) {
        if (name && typeof name === 'string' && handler && typeof handler === 'function') {
            this.handlers[name] = handler;
        }

        // return this for chaining
        return this;
    };

    /*
     * @public
     * Registers a conditional for a custom 'depends' rule
     */

    FormValidator.prototype.registerConditional = function(name, conditional) {
        if (name && typeof name === 'string' && conditional && typeof conditional === 'function') {
            this.conditionals[name] = conditional;
        }

        // return this for chaining
        return this;
    };

    /*
     * @private
     * Determines if a form dom node was passed in or just a string representing the form name
     */

    FormValidator.prototype._formByNameOrNode = function(formNameOrNode) {
        return (typeof formNameOrNode === 'object') ? formNameOrNode : document.forms[formNameOrNode];
    };

    /*
     * @private
     * Adds a file to the master fields array
     */

    FormValidator.prototype._addField = function(field, nameValue)  {
        this.fields[nameValue] = {
            name: nameValue,
            display: field.display || nameValue,
            rules: field.rules,
            depends: field.depends,
            id: null,
            element: null,
            type: null,
            value: null,
            checked: null,
            tipsTarget: field.tipsTarget || $("body"),
            posTarget : field.posTarget || null
        };
    };

    FormValidator.prototype.collectFieldInfo = function(name){
        var element = this.form[name];
        if(this.fields[name] && element && element !== undefined){
            $.extend(this.fields[name],{
                id : attributeValue(element,'id'),
                element : element,
                type : (element.length > 0) ? element[0].type : element.type,
                value : attributeValue(element, 'value'),
                checked : attributeValue(element, 'checked')
            });
            return true;
        }
        return false;
    };

    FormValidator.prototype._deleteField = function(name){
        this.fields[name] && (delete this.fields[name]);
    };

    /*
     * @private
     * Runs the validation when the form is submitted.
     */

    FormValidator.prototype._validateForm = function() {
        this.errors = [];

        for (var key in this.fields) {
            if (this.fields.hasOwnProperty(key) && this.collectFieldInfo(key)) {
                var field = this.fields[key] || {};

                /*
                 * Run through the rules for each field.
                 * If the field has a depends conditional, only validate the field
                 * if it passes the custom function
                 */

                if (field.depends && typeof field.depends === "function") {
                    if (field.depends.call(this, field)) {
                        this._validateField(field);
                    }
                } else if (field.depends && typeof field.depends === "string" && this.conditionals[field.depends]) {
                    if (this.conditionals[field.depends].call(this,field)) {
                        this._validateField(field);
                    }
                } else {
                    this._validateField(field);
                }
            }
        }

        /*
        if (typeof this.callback === 'function') {
            this.callback(this.errors, evt);
        }
        */

        if (this.errors.length > 0) {
            /*
            if (evt && evt.preventDefault) {
                evt.preventDefault();
            } else if (event) {
                // IE uses the global event variable
                event.returnValue = false;
            }
            */
            return false;
        }

        return true;
    };

    FormValidator.prototype.validateField = function(name){
        var field = this.fields[name];
        if(field && this.collectFieldInfo(name)){
            var error = this._validateField(field);
            if(error){
                if(this.showError){
                    this.displayError([error]);
                }
                return error;
            }else{
                this.hideError(this.fields[name]);
            }
        }
        return [];
    };

    /*
     * @private
     * Looks at the fields value and evaluates it against the given rules
     */

    FormValidator.prototype._validateField = function(field) {

        var ruleStr = field.rules;

        if(!ruleStr){
            return;
        }

        if(typeof ruleStr === 'function'){
            ruleStr = ruleStr(field.value);
        }

        var rules = ruleStr.split('|'),
            indexOfRequired = ruleStr.indexOf('required'),
            isEmpty = (!field.value || field.value === '' || field.value === undefined);

        /*
         * Run through the rules and execute the validation methods as needed
         */

        for (var i = 0, ruleLength = rules.length; i < ruleLength; i++) {
            var method = rules[i],
                param = null,
                failed = false,
                parts = ruleRegex.exec(method);

            /*
             * If this field is not required and the value is empty, continue on to the next rule unless it's a callback.
             * This ensures that a callback will always be called but other rules will be skipped.
             */

            if (indexOfRequired === -1 && method.indexOf('callback_') === -1 && isEmpty) {
                continue;
            }

            /*
             * If the rule has a parameter (i.e. matches[param]) split it out
             */

            if (parts) {
                method = parts[1];
                param = parts[2];
            }

            if (method.charAt(0) === '!') {
                method = method.substring(1, method.length);
            }

            /*
             * If the hook is defined, run it to find any validation errors
             */

            var validateResult;

            if (typeof this._hooks[method] === 'function') {
                if ((validateResult = this._hooks[method].apply(this, [field, param])) !== true) {
                    failed = true;
                }
            } else if (method.substring(0, 9) === 'callback_') {
                // Custom method. Execute the handler if it was registered
                method = method.substring(9, method.length);

                if (typeof this.handlers[method] === 'function') {
                    if (this.handlers[method].apply(this, [field.value, param, field]) === false) {
                        failed = true;
                    }
                }
            }

            /*
             * If the hook failed, add a message to the errors array
             */

            if (failed) {
                // Make sure we have a message for this rule
                var source = this.messages[field.name + '.' + method] || this.messages[method] || defaults.messages[method],
                    message = 'An error has occurred with the ' + field.display + ' field.';

                if(typeof validateResult === 'string'){
                    message = validateResult;
                }else if (source) {
                    message = source.replace('%s', field.display);

                    if (param) {
                        message = message.replace('%s', (this.fields[param]) ? this.fields[param].display : param);
                    }
                }

                var error = {
                    id: field.id,
                    element: field.element,
                    name: field.name,
                    message: message,
                    rule: method
                };

                this.errors.push(error);

                return error;

                // Break out so as to not spam with validation errors (i.e. required and valid_email)
                break;
            }
        }
    };

    /*
     * @private
     * Object containing all of the validation hooks
     */

    FormValidator.prototype._hooks = {
        required: function(field) {
            var value = field.value;

            if ((field.type === 'checkbox') || (field.type === 'radio')) {
                return (field.checked === true);
            }

            if(field.type === 'password' && /^\s+$/.test(value)){
                return "密码不能只含空格";
            }

            value = value.replace(/(^\s*)|(\s*$)/, "");

            return (value !== null && value !== '');
        },

        "default": function(field, defaultName){
            return field.value !== defaultName;
        },

        no_blank: function(field){
            return !hasBlankRegex.test(field.value);
        },

        matches: function(field, matchName) {
            var el = this.form[matchName];

            if (el) {
                return field.value === el.value;
            }

            return false;
        },

        valid_email: function(field) {
            return emailRegex.test(field.value);
        },

        valid_emails: function(field) {
            var result = field.value.split(",");

            for (var i = 0, resultLength = result.length; i < resultLength; i++) {
                if (!emailRegex.test(result[i])) {
                    return false;
                }
            }

            return true;
        },

        valid_phone : function(field){
            return phoneRegex.test(field.value);
        },

        valid_tel_phone : function(field){
            return telPhoneRegex.test(field.value);
        },

        valid_identity: function(field){
            var identityNum = field.value,
                Wi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1),
                Ai = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            
            if(identityNum.length != 18 && identityNum.length != 15){
                return false;
            }else if(identityNum.length == 18){
                identityNum = identityNum.substr(0,18);
                if(identityNum.charAt(17) == 'x'){
                    identityNum = identityNum.replace("x","X");
                }

                var checkDigit = identityNum.charAt(17),
                    cardNoSum = 0;

                for(var i = 0; i < identityNum.length - 1; i ++){
                    cardNoSum = cardNoSum + identityNum.charAt(i) * Wi[i];
                }

                var seq = cardNoSum % 11,
                    getCheckDigit = Ai[seq];

                if(checkDigit != getCheckDigit){
                    return false;
                }else{
                    var birth = new Date(identityNum.substring(6,10),identityNum.substring(10,12) - 1,identityNum.substring(12,14)),
                        now = new Date(),
                        years = (now.getTime() - birth.getTime()) / ( 365 * 24 * 60 * 60 * 1000);

                    if(years < 18){
                        return "您未满18岁，无法开户";
                    }else{
                        return true;
                    }
                }

            }else if(/^[1-9]\d{5}\d{2}((0\d)|(1[0-2]))(([0-2]\d)|(3[0-1]))\d{3}$/.test(identityNum)){
                return "根据《中华人民共和国居民身份证法》规定，请使用18位身份证注册！";
            }else if(!/^[\u4E00-\u9FA5\uf900-\ufa2d0-9a-zA-Z()-_<>{}]{2,20}$/.test(identityNum)){
                return false;
            }else{
                return true;
            }
        },

        min_length: function(field, length) {
            if (!numericRegex.test(length)) {
                return false;
            }

            return (field.value.length >= parseInt(length, 10));
        },

        max_length: function(field, length) {
            if (!numericRegex.test(length)) {
                return false;
            }

            return (field.value.length <= parseInt(length, 10));
        },

        exact_length: function(field, length) {
            if (!numericRegex.test(length)) {
                return false;
            }

            return (field.value.length === parseInt(length, 10));
        },

        greater_than: function(field, param) {
            if (!decimalRegex.test(field.value)) {
                return false;
            }

            return (parseFloat(field.value) > parseFloat(param));
        },

        less_than: function(field, param) {
            if (!decimalRegex.test(field.value)) {
                return false;
            }

            return (parseFloat(field.value) < parseFloat(param));
        },

        alpha: function(field) {
            return (alphaRegex.test(field.value));
        },

        alpha_numeric: function(field) {
            return (alphaNumericRegex.test(field.value));
        },

        alpha_dash: function(field) {
            return (alphaDashRegex.test(field.value));
        },

        numeric: function(field) {
            return (numericRegex.test(field.value));
        },

        integer: function(field) {
            return (integerRegex.test(field.value));
        },

        decimal: function(field) {
            return (decimalRegex.test(field.value));
        },

        is_natural: function(field) {
            return (naturalRegex.test(field.value));
        },

        is_natural_no_zero: function(field) {
            return (naturalNoZeroRegex.test(field.value));
        },

        valid_ip: function(field) {
            return (ipRegex.test(field.value));
        },

        valid_base64: function(field) {
            return (base64Regex.test(field.value));
        },

        valid_url: function(field) {
            return (urlRegex.test(field.value));
        },

        valid_credit_card: function(field){
            // Luhn Check Code from https://gist.github.com/4075533
            // accept only digits, dashes or spaces
            if (!numericDashRegex.test(field.value)) return false;

            // The Luhn Algorithm. It's so pretty.
            var nCheck = 0, nDigit = 0, bEven = false;
            var strippedField = field.value.replace(/\D/g, "");

            for (var n = strippedField.length - 1; n >= 0; n--) {
                var cDigit = strippedField.charAt(n);
                nDigit = parseInt(cDigit, 10);
                if (bEven) {
                    if ((nDigit *= 2) > 9) nDigit -= 9;
                }

                nCheck += nDigit;
                bEven = !bEven;
            }

            return (nCheck % 10) === 0;
        },

        is_file_type: function(field,type) {
            if (field.type !== 'file') {
                return true;
            }

            var ext = field.value.substr((field.value.lastIndexOf('.') + 1)),
                typeArray = type.split(','),
                inArray = false,
                i = 0,
                len = typeArray.length;

            for (i; i < len; i++) {
                if (ext == typeArray[i]) inArray = true;
            }

            return inArray;
        }
    };

    //window.FormValidator = FormValidator;
    module.exports = FormValidator;

})(window, document);
