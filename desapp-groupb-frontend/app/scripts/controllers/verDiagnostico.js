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

  });