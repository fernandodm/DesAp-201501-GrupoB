'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:DarTratamientoCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
 var app =  angular.module('myappApp');

 app.controller('DarTratamientoCtrl', function ($http,$scope,$routeParams) {

  $scope.practicas = [];
  $scope.medicamentos = [];



    $http.get('http://localhost:8080/desapp-groupb-backend/rest/treatments/' + $routeParams.idDiagnostico).success(function (diagnostic) {
          
          $scope.nombreDiagnostico = diagnostic.name;

    });


    


   $scope.nuevoTratamiento = function() {

      $http.post('http://localhost:8080/desapp-groupb-backend/rest/treatments/create/' + $routeParams.idDiagnostico + '/' +$scope.repose
       + '/' + $scope.type + '/' + $scope.time + '/' + $scope.practicas + '/' + $scope.medicamentos)
      .success(function (data) {
          $scope.medicamentos.push(data);
      }).success(function (data) {
                    alert("Tratamiento confirmado exitosamente");
                    location = '#/historiasClinicas';
                }).error(function(data,status) {
                        alert("Error (" + status +"): " + "no se pudo confirmar el diagnostico.");
                        location = '#/';
                });
    };

    $scope.agregarPracticaMedica = function() {
    $scope.practicas.push($scope.practica);

    $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/update/' + $routeParams.idDiagnostico + '/' + $scope.practica)
      .success(function () {
  
      });

  };

  $scope.eliminarPracticaMedica = function(practica) {
    var index = $scope.practicas.indexOf(practica);
    $scope.practicas.splice(index,1);
    $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/delete/' + $routeParams.idDiagnostico + '/' + practica)
      .success(function () {
       
  
      });

  };
 
   $scope.agregarMedicamento = function() {

    $http.post('http://localhost:8080/desapp-groupb-backend/rest/treatments/medicine/create/' + $routeParams.idDiagnostico + '/' + $scope.nombreMedicamento + '/' + $scope.concentracion + '/' + $scope.semanas)
      .success(function (data) {
          $scope.medicamentos.push(data);
      });

  };

  $scope.tratamientosSugeridos = function() {

    $http.get('http://localhost:8080/desapp-groupb-backend/rest/treatments/suggested/' + $routeParams.idPaciente + '/' + $scope.nombreDiagnostico).success(function (sugs) {
          
          $scope.sugeridos = sugs;

        });

    };


    $scope.cancelar = function() {
  		alert($routeParams.idPaciente)
  		location = '#/verHistoria/' + $routeParams.idPaciente;
    };   

  });


