function handler(event) {
  var type = event.type;

  if(type === 'mouseover') {
    document.getElementById('window').src ="./images/window-open.png";
  }

  else if(type === 'mouseout') {
    document.getElementById('window').src ="./images/window-close.png";
  }

  else {
    document.getElementById('window').src ="./images/window-broken.png";
  }
  
}
