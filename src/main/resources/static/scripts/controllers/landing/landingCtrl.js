
'use strict';
angular
	.module('app')
		.controller('landingCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'formService',
  function landingCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, formService) {
	
	$scope.$watch("init", function(){
		$scope.loadBalance();
	});
	$scope.balance = {};
	$scope.loadBalance = function(){
		var link = _CONTEXT+'/service/getChestBalance';
		formService.genericGet(link, 0, 0).then(function(response){
			$scope.balance = response.data;
		}).catch(function(response){
			console.error('Error',response);
 			toastr.error(response.data.message, 'Error');
		});
	};
	
  }
]);