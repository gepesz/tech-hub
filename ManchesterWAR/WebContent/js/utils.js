// removes leading/ending whitespaces from a String
String.prototype.trim = function () {
  return this.replace(/(^\s*)|(\s*$)/g, '');
}

// define innerText property for Mozilla
if(IS_MOZILLA) {
		HTMLElement.prototype.__defineGetter__("innerText", function () {
		   var r = this.ownerDocument.createRange();
		   r.selectNodeContents(this);
		   return r.toString().trim();
		});

		HTMLElement.prototype.__defineSetter__("innerText", function (sText) {
			this.innerHTML = sText.replace(/&/g, "&");
		});
}