<head>

    <title>dwr.jsp</title>

    <script type='text/javascript' src='../dwr/engine.js'></script>
    <script type='text/javascript' src='../dwr/util.js'></script>
    <script type='text/javascript' src='../dwr/interface/Demo.js'></script>
    <script type="text/javascript" src="../js/dwr_common.js"></script>
    <script language="javascript">
        var name2 = "";
        function update() {
            var name=document.getElementById("demoName").value;
            console.info(name);
            Demo.sayHello(name, function (data) {
                name2 = data;
                document.getElementById("demoReply").innerHTML=name2;
                alert(name2);
            });
//            alert(name2);
        }

    </script>

</head>

<body>
<p>
    Name:
    <input type="text" id="demoName"/>
    <input value="Send" type="button" onclick="update()"/>
    <br/>
    Reply:
    <span id="demoReply"></span>
</p>

</body>