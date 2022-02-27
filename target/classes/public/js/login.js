

var connection = (function (){

    return{

        conectar : function (user, password){

            console.log(user)
            console.log(password)
            fetch ("http://localhost:4567/Login?name="+user+"&password="+password)
                .then(response => response.json())
                .then(json => console.log(json))
                location.href = 'fondosUsuario.html'

        }

    }


})();