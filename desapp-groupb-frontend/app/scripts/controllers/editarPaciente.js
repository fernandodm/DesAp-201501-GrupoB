'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:HistoriasClinicasCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('EditarPacienteCtrl', function ($http,$scope,$routeParams) {

  	$http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/' + $routeParams.idPaciente)
        .success(function(data) {
        	$scope.paciente = data;
    });

    $scope.editarPaciente = function() {

        $http.put('http://localhost:8080/desapp-groupb-backend/rest/patients/edit/' + $routeParams.idPaciente + '/' + $scope.paciente.firstname + '/' + $scope.paciente.lastname + '/' + $scope.paciente.dni + '/' + $scope.paciente.weight + '/' + $scope.paciente.height)
        .success(function(data) {
                alert('"Paciente "' + $scope.paciente.firstname +'", editado correctamente!!');
                location = '#/historiasClinicas';
        }).error(function(data,status) {
            alert('No se pudo editar el paciente, error (' + status + ')');
        });



        /** $http({
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
                        alert("La edicion del paciente fue exitosa.");
                    location = '#/historiasClinicas';
                    }
                    
                    
                }).error(function(data,status) {
                        alert("Error (" + status +"): " + "la edicion del paciente fallo.");
                        location = '#/crearPaciente';
                });
        
        changeClass();**/
    };

    $scope.cancelar = function() {
        location = '#/';
    };

  });