'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:HistoriasClinicasCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('CrearPacienteCtrl', function ($http,$scope) {

    $scope.crearPaciente = function() {
       /**
        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
        $http.post('http://localhost:8080/desapp-groupb-backend/rest/patients/create/', {firstname: $scope.firstname, lastname: $scope.lastname, dni: $scope.dni})
        .success(function(data) {
                alert('"Paciente "' + $scope.firstname +'", creado correctamente!!');
        }).error(function(data,status) {
            alert('No se pudo crear el paciente, error (' + status + ')');
        });**/

        $http({
            method: 'POST',
            url: 'http://localhost:8080/desapp-groupb-backend/rest/patients/create/',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
            },

            data: {firstname: $scope.firstname, lastname: $scope.lastname, dni: $scope.dni, weight: $scope.weight, height: $scope.height}


        }).success(function (data) {
                    if(data == (-1)){
                        alert("El DNI ya existe!! Ingrese un nuevo DNI.")
                    }else{
                        alert("El paciente fue creado correctamente.");
                    location = '#/historiasClinicas';
                    }
                    
                    
                }).error(function(data,status) {
                        alert("Error (" + status +"): " + "el paciente no se pudo crear.");
                        location = '#/crearPaciente';
                });
        
        changeClass();
    };

    $scope.cancelar = function() {
        location = '#/';
    };

  });