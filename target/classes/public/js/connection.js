

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
                    }else if (data.rol == "USER") {
                        location.href = 'lobyUser.html'
                    }else{
                       location.href = 'lobyAuditor.html'
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
        },

        loadMonto : function(){
            let info = JSON.parse(localStorage.getItem("cedula"));
            fetch("http://localhost:4567/VerFondos?cedula="+info.cedula)
            .then(response => response.json())
            .then(function(data){
                console.log(data)
                $('#nombre').html(data.nombre)
                $('#monto').html(data.fondos)

            })

        },

        verTransferencia : function(){
            fetch("http://localhost:4567/verTransferencia?transacciones=users")
                .then(response => response.json())
                .then(function(data){
                    let html = "<tr>";
                    let monto = 0;
                    let numero = 0;
                    for ( const property in data){
                        console.log(data[property])
                        monto += data[property].cantidad
                        numero +=1
                        html += "<td>" + data[property].cedulaemisor + "</td>"
                        html += "<td>" + data[property].cedulareceptor + "</td>"
                        html += "<td>" + data[property].cantidad + "</td>"
                        html += "</tr>"
                        $('#bodyTable').html(html)
                    }
                    $('#monto').html(monto)
                    $('#total').html(numero)



                })
        },

        modificarMonto :  function (cedula, cantidad){
            console.log(cedula)
            console.log(cantidad)
            fetch("http://localhost:4567/modificarMonto?cedula="+cedula+"&cantidad="+cantidad)
                .then(response => response.json())
                .then(function (data){

                    if (data.modificacion){
                        alert("monto modificado")
                    }
                })
        },

        solicitarSobregiro: function (cedula, monto){
            fetch("http://localhost:4567/solicitarSobregiro?cedula="+cedula+"&monto"+cantidad)
                .then(response => response.json())
                .then(function (data){
                    if(data){
                        alert("Sobregiro Solicitado")
                    }

            })
        }

    }


})();