var connection = (function (){

    return{

        verMonto : function (){
            var url ="http://localhost:4567/fondosUsuario.html";
            fetch (url, {
                method: 'GET',
                credentials: 'include',
                headers: {
                         "Content-type": "application/json"
                         }
            })
                .then(response => response.json())
                .then(json => console.log(json))
        }
    }
})();