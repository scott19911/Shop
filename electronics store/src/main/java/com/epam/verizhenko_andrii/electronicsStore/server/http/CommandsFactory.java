package com.epam.verizhenko_andrii.electronicsStore.server.http;

/**
 *Returns the required command handler based
 */
public class CommandsFactory {
    private final String path;
    private String[] requestParameter;

    public CommandsFactory(String path) {
        this.path = path;
    }

    /**
     * getting the required command executor
     *
     * @return - command based on request
     */
    public Commands getCommand() {
        getRequestParameters(path);
        if (requestParameter[0].equalsIgnoreCase("count")) {
            return new GetCountCommand();
        }
        if (requestParameter[0].equalsIgnoreCase("item?get_info")) {
            return new GetItemCommand(requestParameter[1]);
        }
        return null;
    }

    /**
     * Getting command and request parameters
     *
     * @param request - request String
     */
    private void getRequestParameters(String request) {
        request = request.replaceAll("/shop/", "");
        if (request != null) {
            final String[] rawRequestParameters = request.split("[&;]", -1);
            for (final String rawRequestParameter : rawRequestParameters) {
                requestParameter = rawRequestParameter.split("=", 2);
            }
        }
    }

}
