'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:AgregarDiagnosticoCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
 var app =  angular.module('myappApp');

 app.controller('AgregarDiagnosticoCtrl', AgregarDiagnosticoCtrl);



 function AgregarDiagnosticoCtrl ($http,$scope,$routeParams,$resource, DTOptionsBuilder, DTColumnDefBuilder) {
  var vm = this;
  vm.dtOptions = DTOptionsBuilder.newOptions()
                            .withOption('info',false)
                            .withDisplayLength(10)
                            .withBootstrap();
  vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(0),
        DTColumnDefBuilder.newColumnDef(1),
        DTColumnDefBuilder.newColumnDef(2).notSortable()
    ];


 	$scope.tags = [];
	$scope.sintomas = [];

  $scope.loadTags = function(query) {
    return $http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/symptoms/list', { cache: true}).then(function(response) {
      var sintomas = response.data;
      console.log(sintomas)
      return sintomas.filter(function(sintoma) {
        return sintoma.toLowerCase().indexOf(query.toLowerCase()) != -1;
      });
    });
  };

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

    $scope.asignarDiagnostico = function(diagnostico) {

    	$http.post('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/assignDiagnostic/' + $routeParams.id + '/' + diagnostico.name + '/' + diagnostico.symptoms + '/' + $scope.fecha).success(function (data) {
         
          location = '#/darTratamiento/' + $routeParams.id + '/' +  data.id;

      });


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

  };


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
