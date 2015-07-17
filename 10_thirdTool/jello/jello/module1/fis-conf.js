var version = '20141127'; //clear the frontend cache

fis.config.merge({
    namespace: 'module1',

    project: {
        charset: 'gbk'
    },
    pack: {
        "static/pkg/main.css": [
            "page/main.less",
            "widget/walletUpdatePopUp/walletUpdatePopUp.css",
            "widget/tthPopUp/tthPopUp.css",
        ],
        "static/pkg/main.js": [
            "page/main.js",
            "widget/walletUpdatePopUp/walletUpdatePopUp.js",
            "widget/tthPopUp/tthPopUp.js",
        ]
    },
    deploy: {
        remote: [
            {
                receiver: 'http://10.88.134.193:8071/fisReceiver.jsp',
                from: 'static',
                to: '/'
            }/*,{
             receiver : 'http://10.88.125.76:7001/fisReceiver.jsp',
             from : 'templates',
             to : '/velocity/'
             }*/,
            {
                receiver: 'http://10.88.134.193:8071/fisReceiver.jsp',
                from: 'WEB-INF',
                subOnly: true,
                exclude: 'WEB-INF/server.conf',
                to: '/WEB-INF/'
            }
        ],
        pack: {
            to: '../out',
            exclude: /(\/test\/|server.conf)/i
        }
    },
    roadmap: {
        domain: {
            '**.js': 'http://localhost:8081',
            '**.css': 'http://localhost:8081',
            'image' : ['http://localhost:8081']
        },
        path: [
            {
                reg: /^\/static\/img\/(.*)\.(png|jpg|gif)$/i,
                release: '/${static}/static/baifa/img/$1.$2'},
            {
                reg: /^\/widget\/(.*\.(js|css))$/i,
                isMod: true,//hhw:tag 下面的配置是widget里面的js才会使用amd模式
                release: '${statics}/${namespace}/widget/$1',
                query: '?v=' + version
            },
            {
                reg: /^\/(static)\/(.*)/i,
                release: '${statics}/${namespace}/$2',
                query: '?v=' + version
            },
            {
                reg: /^\/(page)\/(.*\.(js|css))$/i,
                release: '${statics}/${namespace}/page/$2',
                query: '?v=' + version
            }
        ]
    }
});


fis.config.set("settings.optimizer.uglify-js.output.ascii_only", true);