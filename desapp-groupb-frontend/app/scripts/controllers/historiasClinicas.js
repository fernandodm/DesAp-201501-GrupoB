'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:HistoriasClinicasCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
/*angular.module('myappApp')
  .controller('HistoriasClinicasCtrl', function ($http,$scope) {
    
    $http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/list/').success(function (data) {
       //datos lo tenemos disponible en la vista gracias a $scope
       $scope.pacientes = data;
    });

    $scope.verPaciente = function(paciente) {
    location = '#/verHistoria/' + paciente.id;

  };


  });*/

  angular.module('myappApp')
  .controller('HistoriasClinicasCtrl', HistoriasClinicasCtrl);


function HistoriasClinicasCtrl($http, $resource, DTOptionsBuilder, DTColumnDefBuilder) {
    var vm = this;
    $http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/list/').success(function (data) {
       //datos lo tenemos disponible en la vista gracias a $scope
       vm.pacientes = data;
    });

    vm.dtOptions = DTOptionsBuilder.newOptions()
                            .withPaginationType('full_numbers')
                            .withDisplayLength(1)
                            .withBootstrap();
    vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(0),
        DTColumnDefBuilder.newColumnDef(1),
        DTColumnDefBuilder.newColumnDef(2),
        DTColumnDefBuilder.newColumnDef(3).notSortable()
    ];
    

    vm.verPaciente = function(paciente) {
    location = '#/verHistoria/' + paciente.id;

   };
}
/*.controller('HistoriasClinicasCtrl', HistoriasClinicasCtrl);

function HistoriasClinicasCtrl(DTOptionsBuilder, DTColumnBuilder) {
    var vm = this;
    vm.dtOptions = DTOptionsBuilder.newOptions()
        .withOption('ajax', {
         // Either you specify the AjaxDataProp here
         //dataSrc: 'results',
         url: 'http://localhost:8080/desapp-groupb-backend/rest/patients/list/',
         type: 'GET'

     })
     // or here
     .withDataProp('data')
        .withOption('processing', true)
        .withOption('bserverSide', true)
        .withOption('sAjaxDataProp', '')
        .withPaginationType('full_numbers')
        .withDisplayLength(2);
    vm.dtColumns = [
        DTColumnBuilder.newColumn('firstname').withTitle('Nombre'),
        DTColumnBuilder.newColumn('lastname').withTitle('Apellido'),
        DTColumnBuilder.newColumn('dni').withTitle('dni'),
    ];
}*/
