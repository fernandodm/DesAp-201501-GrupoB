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
    'ngTouch'
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
      .when('/agregarHistoria', {
        templateUrl: 'views/agregarHistoria.html',
        controller: 'AgregarHistoriaCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });