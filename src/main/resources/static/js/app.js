var app = angular.module("sodaMachine", []);
app.controller('sodaController', function($scope, $http) {
    $http.get("http://localhost:8080/sodaMachine/inventory")
        .then(function (response) {
            $scope.sodas = response.data;
        });
    $scope.checkInventory = function(){
        $http.get("http://localhost:8080/sodaMachine/totalCount")
            .then(function(response){
                $scope.message = "There are " + response.data +" sodas left";
            });
    }
    $scope.insertCoin = function(){
        closeSodaCans();
        $http.get("http://localhost:8080/sodaMachine/insertCoin")
            .then(function(response) {
                $scope.message = response.data.details;
            });
    };
    $scope.requestRefund = function(){
        $http.get("http://localhost:8080/sodaMachine/requestRefund")
            .then(function(response) {
                $scope.message = response.data.details;
            });
    };
    var refreshSodas = function() {
        $http.get("http://localhost:8080/sodaMachine/inventory")
            .then(function (response) {
                $scope.sodas = response.data;
            });
    }

    var closeSodaCans = function(){
        $scope.coke=false;
        $scope.sprite=false;
        $scope.dietCoke=false;
    }

    var showSodaCan = function(name) {
        if(name == 'coke'){
            $scope.coke=true;
        }else if(name == 'sprite') {
            $scope.sprite=true;
        }else {
            $scope.dietCoke=true;
        }
    }
    var dispenseSoda = function(soda) {
        $http.get("http://localhost:8080/sodaMachine/dispenseSoda/"+ soda.id)
            .then(function success(response) {
                $scope.message = response.data.details;
                showSodaCan(soda.name)
                refreshSodas();
            }, function error(response){
                $scope.message = response.data.details;
            });
    }
    $scope.pressButton = function(soda) {
    	$http.get("http://localhost:8080/sodaMachine/pressButton/"+ soda.count)
            .then(function success() {
                dispenseSoda(soda);
            },function error(response){
                $scope.message = response.data.details;
            });
    };
    $scope.message = "Welcome! Please insert quarter.";
    closeSodaCans();
});