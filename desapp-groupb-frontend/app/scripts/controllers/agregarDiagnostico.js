'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:AgregarDiagnosticoCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
 var app =  angular.module('myappApp');

 app.controller('AgregarDiagnosticoCtrl', function ($http,$scope,$routeParams) {
 	
 	$scope.tags = [];
	$scope.sintomas = [];
  $scope.crearDiagnostico = function() {


     if($scope.sintomas.length > 0){  	
   		$http({
            method: 'POST',
            url: 'http://localhost:8080/desapp-groupb-backend/rest/diagnoses/create/',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
            },

            data: {id: $routeParams.id, name: $scope.nombre, symptoms: $scope.sintomas, date: $scope.fecha}


        }).success(function (data) {
                    location = '#/darTratamiento/' + $routeParams.id + '/' +  data.id;
                });
        
        changeClass();
      }else{
        alert("Agregue al menos un sintoma")
      }

    }; 

    $scope.cancelar = function() {
  		
  		location = '#/verHistoria/' + $routeParams.id;
    };  

    $scope.eliminarSintoma = function(tag){
      var index = $scope.sintomas.indexOf(tag.text);
      $scope.sintomas.splice(index,1);

      if($scope.sintomas.length > 0){
        diagnosticosSugeridos()
      }else{
        $scope.diagnosticos = [];
      }
    };

    $scope.agregarSintoma = function() {
      $scope.sintomas = [];
      var i;
      for (i = 0; i < $scope.tags.length; i++) { 
        $scope.sintomas.push($scope.tags[i].text);
      }

      diagnosticosSugeridos()

    };

    function diagnosticosSugeridos(){
      $http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/' + $scope.sintomas).success(function (data) {
          
          $scope.diagnosticos = data;

      });
    }

  });


 app.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress ", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });
 
                event.preventDefault();
            }
        });
    };
  });
