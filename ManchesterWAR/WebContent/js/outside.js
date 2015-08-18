// focuses the blinking cursor on the username input field of the login page.
function focusOnUserName() {
  try {
    document.getElementsByName('username')[0].focus();
  } catch(e) {}
}