'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:AgregarDiagnosticoCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('AgregarDiagnosticoCtrl', function ($http,$scope,$routeParams) {
/**
    $scope.agregarSintoma = function() {
    	$scope.sintomas.push($scope.sintoma)
    	$http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/' + $scope.sintomas).success(function (data) {
       		
       		$scope.diagnosticos = data;

   		});

    };

    $scope.crearDiagnostico = function() {

    	$http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/' + $routeParams.id).success(function (paciente) {
       		
       		$http.post('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/create/' + paciente + '/' + $scope.nombre + '/' + $scope.sintomas)
    			.success(function (data) {
       		
       		

   			});

   		});
    };
**/
  });