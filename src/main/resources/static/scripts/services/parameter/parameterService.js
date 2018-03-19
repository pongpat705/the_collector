angular.module('app')
  .factory('ParameterService', ['$http', '$rootScope', function ParameterService($http, $rootScope) {
    return {
    	getAllParameter: function(params) {
            return $http.get(CONTEXT+'/api/parameterinfos',{params : params});
        },
		getCombos: function() {
	        return $http.get(CONTEXT+'/service/getCombos');
	    }
    };
  }])