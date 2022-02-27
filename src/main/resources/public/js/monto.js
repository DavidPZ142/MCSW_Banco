var connection = (function (){

    return{

        verMonto : function (){


            fetch ("http://localhost:4567/fondosUsuario.html")
                .then(response => response.json())
                .then(json => console.log(json))

        }

    }


})();