'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:ReportesCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('ReportesCtrl', function ($http,$scope) {



  	$http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/symptoms/lastMonth').success(function (data) {
       $scope.sintomasMes = data;
    });

    $http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/symptoms/semester').success(function (data) {
       $scope.sintomasSemestre = data;
    });

    $http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/symptoms/year').success(function (data) {
       $scope.sintomasAnho = data;
    });
    

  });