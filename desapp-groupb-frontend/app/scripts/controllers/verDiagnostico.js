'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:verDiagnosticoCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('VerDiagnosticoCtrl', function ($http,$scope,$routeParams) {

  	$http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/diagnostic/' + $routeParams.idDiagnostico)
        .success(function(data) {
        	var date = new Date(data.date);
        	$scope.diagnostico = data;
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
      console.log(sintomas);
       return sintomas.filter(function(sintoma) {
        return sintoma.toLowerCase().indexOf(query.toLowerCase()) != -1;
       });
      });
    };


    $scope.guardarDiagnostico = function() {
        
        $http.put('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/update/' + $scope.diagnostico.id + '/' + $scope.diagnostico.name + '/' +$scope.diagnostico.symptoms + '/' + $scope.diagnostico.date)
            .success(function(data) {
            
        });

        $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/update/' + $scope.diagnostico.treatment.id + '/' + $scope.repose + '/' + $scope.type + '/' + $scope.time + '/' + $scope.practicas)
            .success(function(data) {
            
        });
    };

    $scope.agregarPracticaMedica = function() {

      if($scope.practicas.indexOf($scope.practica) == -1){
        $scope.practicas.push($scope.practica);
      
        $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/update/' + $scope.diagnostico.id + '/' + $scope.practica)
          .success(function () {
  
          });
      }
    
   };

  $scope.eliminarPracticaMedica = function(practica) {
    var index = $scope.practicas.indexOf(practica);
    $scope.practicas.splice(index,1);
    $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/delete/' + $scope.diagnostico.id + '/' + practica)
      .success(function () {
       
  
      });

  };
 
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
  });