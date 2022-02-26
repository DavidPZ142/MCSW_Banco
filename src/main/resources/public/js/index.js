


var connection = (function (){

    return{

        conectar : function (){

            fetch ("http://localhost:4567/Login?name=Daniel&password=123")
                .then(response => response.json())
                .then(json => location.href = 'login.html')



        }

    }


})();