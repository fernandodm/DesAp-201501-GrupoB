'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:HistoriasClinicasCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('HistoriasClinicasCtrl', function ($http,$scope) {
  	
    $http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/list/').success(function (data) {
       //datos lo tenemos disponible en la vista gracias a $scope
       $scope.pacientes = data;
    });
  });

