'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:HistoriasClinicasCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('HistoriasClinicasCtrl', HistoriasClinicasCtrl);


function HistoriasClinicasCtrl($http, $resource, DTOptionsBuilder, DTColumnDefBuilder) {
    var vm = this;
     
    $http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/list/').success(function (data) {
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
        DTColumnDefBuilder.newColumnDef(3),
        DTColumnDefBuilder.newColumnDef(4),
        DTColumnDefBuilder.newColumnDef(5).notSortable()
    ];

    vm.verPaciente = function(paciente) {
      location = '#/verHistoria/' + paciente.id;
    };

    vm.editarPaciente = function(paciente) {
      location = '#/editarPaciente/' + paciente.id;
    };
}