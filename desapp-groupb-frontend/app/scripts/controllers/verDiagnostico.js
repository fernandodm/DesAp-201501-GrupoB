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
    });

  });