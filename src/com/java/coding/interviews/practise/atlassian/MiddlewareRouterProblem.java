package com.java.coding.interviews.practise.atlassian;

import java.util.List;

/**
 * ✅ Java Solution
 *
 * We’ll implement a simple route matcher using:
 * 	•	Splitting by /
 * 	•	Matching segments
 * 	•	Using {} to represent variables
 *
 * 	🧠 Time & Space Complexity
 * 	•	Time: O(N * L) where:
 * 	•	N = number of routes
 * 	•	L = average number of path segments
 * 	•	Space: O(1) extra per call
 */
public class MiddlewareRouterProblem {

    public static void main(String[] args) {
        List<Route> routes = List.of(
                new Route("/user/{id}/profile", "getUserProfile"),
                new Route("/user/{id}/settings", "getUserSettings"),
                new Route("/product/{productId}/details", "getProductDetails")
        );

        System.out.println(matchRoute("/user/123/profile", routes));      // getUserProfile
        System.out.println(matchRoute("/user/456/settings", routes));     // getUserSettings
        System.out.println(matchRoute("/product/999/details", routes));   // getProductDetails
        System.out.println(matchRoute("/user/123/orders", routes));
    }

    static class Route{
        String path;
        String action;
        Route(String path, String action) {
            this.path = path;
            this.action = action;
        }
    }

    public static String matchRoute(String inputURL, List<Route> routes){
        if(inputURL==null || inputURL.length()==0 || routes==null || routes.size()==0)
            throw new IllegalArgumentException("Input URL or Routes is null or empty");
        String[] inputParts = inputURL.split("/");
        for(Route route : routes){
            String[] routeParts = route.path.split("/");
            if(inputParts.length!=routeParts.length) continue;
            boolean match = true;
            for(int i=0;i<routeParts.length;i++){
                if(routeParts[i].startsWith("{") && routeParts[i].endsWith("}")) {
                    continue;
                }
                if(!routeParts[i].equals(inputParts[i])){
                    match = false;
                    break;
                }
            }
            if(match) return route.action;
        }
        return "NOT FOUND";
    }
}
