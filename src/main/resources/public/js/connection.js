

var connection = (function (){

    return{

        conectar : function (user, password){

            console.log(user)
            console.log(password)
            fetch ("http://localhost:4567/Login?name="+user+"&password="+password)
                .then(response => response.json())
                .then(function (data){
                    console.log(data)
                    localStorage.setItem("cedula", JSON.stringify(data))

                    if(data.rol == "ADMIN"){
                        location.href = 'lobyAdmin.html'
                    }else {

                        location.href = 'lobyUser.html'
                    }

                })
        },

        registrarUser: function (cedula, nombre, apellido, correo, contrasena, fondos){
            console.log('entre')
            fetch( "http://localhost:4567/Registrar?name="+cedula+"&nombre="+nombre+"&apellido="+apellido+"&correo="+correo+"&password="+contrasena+"&fondos="+fondos)
                .then(response => response.json())
                .then( function (data){
                    if(data){
                        alert("Usuario Registrado")
                    }
                })
        },

        transferencia: function (cedulaDestino, monto){
            let info = JSON.parse(localStorage.getItem("cedula"))
            fetch("http://localhost:4567/Transferencia?ccOrigen="+info.cedula+"&ccDestino="+cedulaDestino+"&monto="+monto)
                .then(response => response.json())
                .then( function (data){
                    console.log(data.transferencia)
                    if (data){
                        alert("transerencia realizada")
                    }
                })
        }

    }


})();