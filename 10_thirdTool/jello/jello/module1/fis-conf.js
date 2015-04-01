var version = '20141127'; //clear the frontend cache

fis.config.merge({
	namespace: 'module1',

    project : {
        charset : 'utf-8'
    },
    pack : {
        "static/pkg/main.css" : [
            "page/main.less",
            "widget/walletUpdatePopUp/walletUpdatePopUp.css",
            "widget/tthPopUp/tthPopUp.css",
        ],
        "static/pkg/main.js" : [
            "page/main.js",
            "widget/walletUpdatePopUp/walletUpdatePopUp.js",
            "widget/tthPopUp/tthPopUp.js",
        ]
    },
    deploy:{
        remote : [{
            receiver : 'http://10.88.115.62:7001/fisReceiver.jsp',
            from : 'static',
            to : '/' 
        }/*,{ 
            receiver : 'http://10.88.125.76:7001/fisReceiver.jsp',
            from : 'templates',
            to : '/velocity/'
        }*/,{ 
            receiver : 'http://10.88.115.62:7001/fisReceiver.jsp',
            from : 'WEB-INF',
            subOnly : true,
            exclude : 'WEB-INF/server.conf',
            to : '/WEB-INF/'
        }],
        pack : { 
            to : './output/patch/tradeapp/webTrade/',
            exclude : /(\/test\/|server.conf)/i
        }
    }
});
//hhw:tag �����������widget�����js�Ż�ʹ��amdģʽ
fis.config.data.roadmap.path.splice(1,0,{
    reg: /^\/widget\/(.*\.(js|css))$/i,
    isMod: true,
    release: '${statics}/${namespace}/widget/$1',
    query : '?v=' + version
});

fis.config.data.roadmap.path.splice(2,0,{
    reg: /^\/(static)\/(.*)/i,
    release: '${statics}/${namespace}/$2',
    query : '?v=' + version
});

fis.config.data.roadmap.path.splice(3,0,{
    reg: /^\/(page)\/(.*\.(js|css))$/i,
    release: '${statics}/${namespace}/page/$2',
    query : '?v=' + version
});

fis.config.set("settings.optimizer.uglify-js.output.ascii_only",true);