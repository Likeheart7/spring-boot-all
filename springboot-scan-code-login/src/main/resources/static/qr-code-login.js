const path = "://localhost:8001";
const getQrPath = "http" + path + "/login/getLoginQr";
const wsPath = "ws" + path + "/ws/";

$(document).ready(() => {
    initQrImg()
})

function initQrImg() {
    $('qrImgDiv').empty()

    var xmlhttp;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", getQrPath, true);
    xmlhttp.responseType = "blob";
    xmlhttp.onload = function() {
        console.log(this);
        uuid = this.getResponseHeader("uuid");
        if (this.status == 200) {
            var blob = this.response;
            var img = document.createElement('img')
            img.className = 'qrCodeBox-img'
            img.onload = (e) => {
                window.URL.revokeObjectURL(img.src)
            }
            img.src = window.URL.createObjectURL(blob)
            document.getElementById("qrImgDiv").appendChild(img)
            initWebSocket()
        }
    }
    xmlhttp.send()

}

const initWebSocket = () => {
    if (typeof WebSocket == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        // 支持websocket
        const wsPathStr = wsPath + uuid;
        socket = new WebSocket(wsPathStr)
        socket.open = () => {
            console.log('Socket opened');
        }
        socket.onmessage = (msg) => {
            console.log(msg.data);
            let data = JSON.parse(msg.data)
            if (data.code == 200) {
                alert("登录成功")
                window.sessionStorage.uuid = uuid
                window.sessionStorage.userId = userId
            }else {
                socket.close()
                initQrImg()
            }
        }
        socket.onclose = () => {
            console.log("Socket 已关闭");
        }
        socket.onerror = () => {
            alert("Socket 发生错误")
        }
    }
}