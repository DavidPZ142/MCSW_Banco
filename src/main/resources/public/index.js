


var connection = (function (){

    return{

        conectar : function (){

            fetch ("http://localhost:4567/Login.html")
                .then(response => response.json())
                .then(json => console.log(json))

        }

    }


})();