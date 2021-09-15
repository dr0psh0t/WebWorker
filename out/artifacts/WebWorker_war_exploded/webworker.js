/*var i = 0;

function timedCount() {
    i = i + 1;
    postMessage(i);
    setTimeout("timedCount()", 500);
}

timedCount();*/

onmessage = function(e) {
    console.log('Message received from main script: '+e.data);
    postMessage('Posting message back to main script');
};