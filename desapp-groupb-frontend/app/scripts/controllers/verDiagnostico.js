'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:verDiagnosticoCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
 var app =  angular.module('myappApp');

 app.controller('VerDiagnosticoCtrl', function ($http,$scope,$routeParams) {



  	$http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/diagnostic/' + $routeParams.idDiagnostico)
        .success(function(data) {
        	var date = new Date(data.date);

        	$scope.diagnostico = data;
            $scope.tags = $scope.diagnostico.symptoms;
        	$scope.diagnostico.date = date.getDate() + '/' + date.getMonth() + '/' +  date.getFullYear();

        	$scope.practicas = $scope.diagnostico.treatment.medicalPractices
        	$scope.medicamentos = $scope.diagnostico.treatment.medicines

        	$scope.repose = $scope.diagnostico.treatment.repose


        	if($scope.repose){
        		cambiarValorType()
        		$scope.time = $scope.diagnostico.treatment.time
        	}
    });

    function cambiarValorType(){
    	
    	if($scope.diagnostico.treatment.type == "Total"){
    		$scope.type = true
    	}else{
    		$scope.type = false
    	}
    }

    $scope.loadTags = function(query) {
    return $http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/symptoms/list', { cache: true}).then(function(response) {
      var sintomas = response.data;
      
       return sintomas.filter(function(sintoma) {
        return sintoma.toLowerCase().indexOf(query.toLowerCase()) != -1;
       });
      });
    };

    $scope.eliminarSintoma = function(tag){
      var index = $scope.diagnostico.symptoms.indexOf(tag.text);
      $scope.diagnostico.symptoms.splice(index,0);
       $scope.actualizarSintomas();

    };

    $scope.actualizarSintomas = function() {
      $scope.diagnostico.symptoms = [];
      var i;
      for (i = 0; i < $scope.tags.length; i++) { 
        $scope.diagnostico.symptoms.push($scope.tags[i].text);
      }
    };


    $scope.guardarDiagnostico = function() {
        $scope.actualizarSintomas();

        $http.put('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/update/' + $scope.diagnostico.id + '/' + $scope.diagnostico.name + '/' + $scope.diagnostico.symptoms + '/' + $scope.diagnostico.date)
            .success(function(data) {
            
        });

        if(seLlenaronCamposPrincipales()){

            
            
        if($scope.time == null){
          $scope.time = 0;
        }

        if($scope.practicas.length == 0){

            guardarTratamientoSinPracticas()

        }else{
  
        $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/update/' + $scope.diagnostico.treatment.id + '/' + $scope.repose + '/' + $scope.type + '/' + $scope.time + '/' + $scope.practicas)
        .success(function (data) {
       
        }).success(function (data) {
                    alert("Diagnostico editado exitosamente");
                    location = '#/verHistoria/' + $routeParams.idPaciente;
                }).error(function(data,status) {
                        alert("Error (" + status +"): " + "no se pudo editar el diagnostico.");
                        location = '#/verHistoria/' + $routeParams.idPaciente;
                });
        }
      }

      

    };

    function guardarTratamientoSinPracticas(){

        $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/update/' + $scope.diagnostico.treatment.id + '/' + $scope.repose + '/' + $scope.type + '/' + $scope.time)
        .success(function (data) {

        }).success(function (data) {
                    alert("Diagnostico editado exitosamente");
                    location = '#/verHistoria/' + $routeParams.idPaciente;
                }).error(function(data,status) {
                        alert("Error (" + status +"): " + "no se pudo editar el diagnostico.");
                        location = '#/verHistoria/' + $routeParams.idPaciente;
                });
    }


    $scope.agregarPracticaMedica = function() {

      if($scope.practicas.indexOf($scope.practica) == -1){
        $scope.practicas.push($scope.practica);
      
        $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/update/' + $scope.diagnostico.id + '/' + $scope.practica)
          .success(function () {
  
          });
      }
      $scope.practica = "";
   };

  $scope.eliminarPracticaMedica = function(practica) {
    var index = $scope.practicas.indexOf(practica);
    $scope.practicas.splice(index,1);
    $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/delete/' + $scope.diagnostico.id + '/' + practica)
      .success(function () {
       
  
      });

  };

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
 
   $scope.agregarMedicamento = function() {

    if(!existeMedicamento($scope.nombreMedicamento)){

      $http.post('http://localhost:8080/desapp-groupb-backend/rest/treatments/medicine/create/' + $scope.diagnostico.id  + '/' + $scope.nombreMedicamento + '/' + $scope.concentracion + '/' + $scope.semanas)
        .success(function (data) {
          $scope.medicamentos.push(data);
      });
    }
  };

  function existeMedicamento(nombre){

    for (var i = 0; i < $scope.medicamentos.length; i++) {
      if($scope.medicamentos[i].drugName == nombre){
        return true;
      }
    }
    return false;
  }

  
  $scope.cancelar = function() {
        location = '#/verHistoria/' + $routeParams.idPaciente;
    };  

  });


app.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress ", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });
 
                event.preventDefault();
            }
        });
    };
  });
