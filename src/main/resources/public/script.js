

const fetchUserLogged = () => {

axios.get('http://localhost:8080/user/logged')
.then(response => {

    const user = response.data.username;
    document.querySelector('.navbar-brand').textContent = user;

})
.catch(error => console.error(error));

};
fetchUserLogged();