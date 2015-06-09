'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:DarTratamientoCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
 var app =  angular.module('myappApp');

 app.controller('DarTratamientoCtrl', function ($http,$scope,$routeParams) {

  $scope.practicas = [];

 

 	$scope.sugerirTratamientos = function() {
 		$scope.tratamientos = [];
 		var i;
 		for (i = 0; i < $scope.tags.length; i++) { 
    		$scope.tratamientos.push($scope.tags[i].text);
		}

		$http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/' + $scope.sintomas).success(function (data) {
       		
       		$scope.tratamientos = data;

   		});

    };

   $scope.nuevoTratamiento = function() {
       /**
        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
        $http.post('http://localhost:8080/desapp-groupb-backend/rest/patients/create/', {firstname: $scope.firstname, lastname: $scope.lastname, dni: $scope.dni})
        .success(function(data) {
                alert('"Paciente "' + $scope.firstname +'", creado correctamente!!');
        }).error(function(data,status) {
            alert('No se pudo crear el paciente, error (' + status + ')');
        });**/

        $http({
            method: 'POST',
            url: 'http://localhost:8080/desapp-groupb-backend/rest/treatments/create/',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
            },

            data: {repose: $scope.repose, type: $scope.type, time: $scope.time, medicalPractices: $scope.practicas, medicines: $scope.medicines}


        }).success(function () {
                    alert("Diagnostico confirmado exitosamente");
                    location = '#/historiasClinicas';
                }).error(function(data,status) {
                        alert("Error (" + status +"): " + "no se pudo confirmar el diagnostico.");
                        location = '#/';
                });
        
        changeClass();
    };

    $scope.agregarPracticaMedica = function() {
    $scope.alergias.push($scope.alergia);

    $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/update/' + $routeParams.id + '/' + $scope.alergia)
      .success(function () {
       
  
      });

  };

  $scope.eliminarPracticaMedica = function(practica) {
    var index = $scope.practicas.indexOf(practica);
    $scope.practicas.splice(index,1);
    $http.put('http://localhost:8080/desapp-groupb-backend/rest/treatments/delete/' + $routeParams.id + '/' + practica)
      .success(function () {
       
  
      });

  };
 


    $scope.cancelar = function() {
  		
  		location = '#/';
    };   

  });


