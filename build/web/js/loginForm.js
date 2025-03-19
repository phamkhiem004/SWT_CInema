/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
const signUpForm = document.querySelector('.sign_up-container');
const signInForm = document.querySelector('.sign-in-container');
const overlayLeft = document.querySelector('.overlay-left');
const overlayRight = document.querySelector('.overlay-right');

// Toggle between sign-up and sign-in forms
signUpButton.addEventListener('click', () => {
    container.classList.add("left-panel-active");
    signInForm.reset();
});

signInButton.addEventListener('click', () => {
    container.classList.remove("left-panel-active");
    signUpForm.reset();
});
// Get the overlay panels
// Toggle between sign-up and sign-in forms using overlays
overlayLeft.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
    signUpForm.reset();
});

overlayRight.addEventListener('click', () => {
    container.classList.add("right-panel-active");
    signInForm.reset();
});
// signUpButton.addEventListener('click', () => {
//     container.classList.add("sign-up-active");
//     signInForm.reset(); // Clear the login form
// });
//
// signInButton.addEventListener('click', () => {
//     container.classList.remove("sign-up-active");
//     signUpForm.reset(); // Clear the sign-up form
// });