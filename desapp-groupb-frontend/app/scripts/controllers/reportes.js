'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:ReportesCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('ReportesCtrl', ReportesCtrl);

  function ReportesCtrl($http, $resource, DTOptionsBuilder, DTColumnDefBuilder) {
    var vm = this;
     
    $http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/symptoms/lastMonth').success(function (data) {
       
       vm.sintomasMes = data;
    });

    $http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/symptoms/semester').success(function (data) {
       vm.sintomasSemestre = data;
    });

    $http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/symptoms/year').success(function (data) {
       vm.sintomasAnho = data;
    });

    vm.dtOptions = DTOptionsBuilder.newOptions()
                            .withDisplayLength(10)
                            .withBootstrap();
    vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(0),
        DTColumnDefBuilder.newColumnDef(1),
    ];

  };