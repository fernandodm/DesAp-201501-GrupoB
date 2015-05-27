'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:agregarHistoriaCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('VerHistoriaCtrl', function ($scope,$http,$routeParams) {

    
	$http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/' + $routeParams.id).success(function (data) {
       //datos lo tenemos disponible en la vista gracias a $scope
       $scope.paciente = data;

    });
  });