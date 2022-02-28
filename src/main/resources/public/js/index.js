


var connection = (function (){

    return{

        conectar : function (){
            location.href = 'login.html'


        },

        infoUser : function () {
            let info = JSON.parse(localStorage.getItem("cedula"))
            $('#nombre').html(info.nombre)
            $('#fondos').html(info.fondos)
        }

    }


})();