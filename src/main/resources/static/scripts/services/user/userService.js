angular.module('app')
  .factory('userService', ['$http', '$rootScope', function UserService($http, $rootScope) {
    return {
    	getAllUser: function(page, size) {
            return $http.get(CONTEXT+'/api/users',{params:{'page':page, 'size':size}});
        },
        getUserByName: function(page, size) {
        	return $http.get(CONTEXT+'/api/users/search/findByUserNameContaining',{params:{'page':page, 'size':size}});
        },
        addUser: function(user){
        	return $http.post(CONTEXT+'/service/addUser',user);
        },
        delUsers : function(id){
      	  return $http.patch(CONTEXT+'/service/deleteUsers/'+id);
        },
        getUserForpatch : function(id){
    	  return $http.get(CONTEXT+'/service/getUserForPatch/'+id);
        },
        patchUser : function(user, id){
        	return $http.patch(CONTEXT+'/service/patchUser/'+id,user);
        }
    };
  }])