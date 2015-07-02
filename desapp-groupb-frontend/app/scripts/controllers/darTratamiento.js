'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:DarTratamientoCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
 var app =  angular.module('myappApp');

 app.controller('DarTratamientoCtrl', function ($http,$scope,$routeParams,ngDialog) {

  $scope.practicas = [];
  $scope.medicamentos = [];



    $http.get('http://localhost:8080/desapp-groupb-backend/rest/treatments/' + $routeParams.idDiagnostico).success(function (diagnostic) {
          
          $scope.nombreDiagnostico = diagnostic.name;
    });

    $scope.clickToOpen = function (tratamiento) {
        $scope.tratamiento = tratamiento
        ngDialog.open({ template: 'templateId', 
                        scope: $scope});
    };

    $scope.resetearCampos = function() {
      $scope.type = null;
      $scope.time = null;
    };
    


   $scope.nuevoTratamiento = function() {
      
      if(seLlenaronCamposPrincipales()){

        if($scope.time == null){
          $scope.time = 0;
        }

        if($scope.practicas.length == 0){
            guardarTratamientoSinPracticas()
        }else{
  
        $http.post('http://localhost:8080/desapp-groupb-backend/rest/treatments/create/' + $routeParams.idDiagnostico + '/' + $scope.repose
        + '/' + $scope.type + '/' + $scope.time + '/' + $scope.practicas)
        .success(function (data) {
          $scope.medicamentos.push(data);
        }).success(function (data) {
                    alert("Tratamiento confirmado exitosamente");
                    location = '#/historiasClinicas';
                }).error(function(data,status) {
                        alert("Error (" + status +"): " + "no se pudo confirmar el diagnostico.");
                        location = '#/';
                });
        }
      }
    };

    function guardarTratamientoSinPracticas(){

        $http.post('http://localhost:8080/desapp-groupb-backend/rest/treatments/create/' + $routeParams.idDiagnostico + '/' + $scope.repose
        + '/' + $scope.type + '/' + $scope.time)
        .success(function (data) {
          $scope.medicamentos.push(data);
        }).success(function (data) {
                    alert("Tratamiento confirmado exitosamente");
                    location = '#/historiasClinicas';
                }).error(function(data,status) {
                        alert("Error (" + status +"): " + "no se pudo confirmar el diagnostico.");
                        location = '#/';
                });
    }


    function seLlenaronCamposPrincipales(){

      if($scope.repose == null){
        alert("Elija si esta en reposo o no");
        return false;
      }else{
         if(($scope.repose == true) && (($scope.type == null) || ($scope.time == null))){
            alert("Llene los campos tipo de reposo y duracion");
            return false;
         }
      }

      return true;

    }

    $scope.retornarReposo = function(reposo) {

      if(reposo == true){
        return "Si"
      }else{
        return "No"
      }
    
   };

   $scope.retornarTipo = function(tipo) {

      if(tipo == null){
        return "-"
      }else{
        return tipo
      }
    
   };

   $scope.retornarTiempo = function(tiempo) {

      if(tiempo == 0){
        return "-"
      }else{
        return tiempo
      }
    
   };

    $scope.agregarPracticaMedica = function() {

      if($scope.practicas.indexOf($scope.practica) == -1){
        $scope.practicas.push($scope.practica);
      
        $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/update/' + $routeParams.idDiagnostico + '/' + $scope.practica)
          .success(function () {
  
          });
      }
    
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


