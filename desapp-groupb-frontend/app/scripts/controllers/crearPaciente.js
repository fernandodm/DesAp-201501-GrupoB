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
    //$scope.objectPaciente = { 'firstname':'', 'lastname':'', 'dni':''};
    $scope.crearPaciente = function() {
        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
        $http.post('http://localhost:8080/desapp-groupb-backend/rest/patients/create/', {firstname: $scope.firstname, lastname: $scope.lastname, dni: $scope.dni})
        .success(function() {
                alert('"Paciente "' + $scope.firstname +'", creado correctamente!!');
                
        }).error(function(data,status) {
            alert('No se pudo crear el paciente, error (' + status + ')');
        });
    };
  });