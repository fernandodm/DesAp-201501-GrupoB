'use strict';

/**
 * @ngdoc overview
 * @name myappApp
 * @description
 * # myappApp
 *
 * Main module of the application.
 */
angular
  .module('myappApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngTagsInput'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/historiasClinicas', {
        templateUrl: 'views/historiasClinicas.html',
        controller: 'HistoriasClinicasCtrl'
      })
      .when('/crearPaciente', {
        templateUrl: 'views/crearPaciente.html',
        controller: 'CrearPacienteCtrl'
      })
      .when('/verHistoria/:id', {
        templateUrl: 'views/verHistoria.html',
        controller: 'VerHistoriaCtrl'
      })
      .when('/agregarDiagnostico/:id', {
        templateUrl: 'views/agregarDiagnostico.html',
        controller: 'AgregarDiagnosticoCtrl'
      })

      .when('/darTratamiento/:id', {
        templateUrl: 'views/darTratamiento.html',
        controller: 'DarTratamientoCtrl'
      })
      .when('/reportes', {
        templateUrl: 'views/reportes.html',
        controller: 'ReportesCtrl'

      })
      .otherwise({
        redirectTo: '/'
      });
  });
