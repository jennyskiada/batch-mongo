package gr.avalanche.mongobatch.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Pager Object
 * @author eskiada
 */
public class Pager {

    private static final String FIRST_PAGE = "first";
    private static final String LAST_PAGE = "last";
    private static final String CURRENT_PAGE = "current";
    private static final String PREVIOUS_PAGE = "previous";
    private static final String NEXT_PAGE = "next";
    private static final String START_FROM = "startFrom";
    private static final String PAGE_SIZE = "pageSize";

    //Constructor fields
    private String baseUrl;
    private Integer resultsPerPage;
    private Integer resultsInTotal;
    private Integer startFrom;
    private Map<String, String[]> requestParameters;

    private Integer pages;          //Calculated Value
    private Integer currentPage;    //Calculated Value

    private Map<String, String[]> elements = new LinkedHashMap<String, String[]>(); //The Pager's Result

    /**
     * Pager Constructor
     * @param baseUrl The Base URL
     * @param resultsPerPage Results Per Page
     * @param startFrom Result To Start From
     * @param resultsInTotal Results In Total
     * @param requestParameters Http Request Parameters
     */
    public Pager(String baseUrl, Integer resultsPerPage, Integer startFrom, Integer resultsInTotal, Map<String, String[]> requestParameters){
        this.baseUrl = baseUrl;
        this.resultsPerPage = resultsPerPage;
        this.startFrom = startFrom;
        this.resultsInTotal = resultsInTotal;
        this.requestParameters = requestParameters;
        //Calculate Needed Values And Build The Results Map
        calculateNumberOfPages();
        if(this.pages > 0 && this.startFrom< this.resultsInTotal){
            calculateCurrentPage();
            buildResultsMap();
        }
    }

    /**
     * Calculate The Number Of Pages
     */
    private void calculateNumberOfPages() {
        if(resultsInTotal >0){
            this.pages = (resultsInTotal % resultsPerPage ==0 ? resultsInTotal / resultsPerPage : resultsInTotal / resultsPerPage +1);
        } else {
            this.pages = 0; //No Results. The Pager Should Not Return Elements
        }
    }

    /**
     * Calculate The Current Page
     */
    private void calculateCurrentPage() {
        this.currentPage = this.startFrom == 0 ? 0 : this.startFrom / this.resultsPerPage;
    }

    /**
     * A Map<String, String[]> Containing All The Available Info To Build The View's Pagination. The Map's Key Is The Value To
     * Render (i.e. first, previous Or A Page Number And The Value Is A String[] With The First Item Containing The Anchor's href
     * And The Second Defining If This Element Is The Current Page (current For The Current Page And null For All Other)
     */
    private void buildResultsMap() {
        int startFromLoop = 0;
        for(int i=0; i< this.pages; i++){
           /*
             * First + Previous Page Anchors Only If Are Needed
             */
            if(i==0 && this.currentPage>0){
                elements.put(FIRST_PAGE, new String[]{buildUrl(0,false), null});
                elements.put(PREVIOUS_PAGE, new String[]{buildUrl((currentPage-1)*resultsPerPage, true), null});
            }
            /**
             * Excluding The Previous, Next Buttons, We Want To Show 5 Pages At Maximum
             */
            if(currentPage==i || Math.abs(currentPage - i)<=2) {
                elements.put(Integer.toString(i + 1), new String[]{ buildUrl(startFromLoop, true), currentPage==i ? CURRENT_PAGE : null });

            }
            /*
             * Next + Last Page Anchors Only If Are Needed
             */
            if(i==pages-1 && currentPage<pages-1) {
                elements.put(NEXT_PAGE, new String[] { buildUrl((currentPage+1)*resultsPerPage, true), null });
                elements.put(LAST_PAGE, new String[] { buildUrl((pages-1)*resultsPerPage, true), null });
            }
            startFromLoop += resultsPerPage; //Increase Before Continuing

        }
    }

    /**
     * Build An Anchor's href
     * @param startFrom The Starting Value For The Results Page
     * @param contained If false We Do Not Want To Include The startFrom Parameter In The Resulting href
     * @return Anchor's href
     */
    private String buildUrl(int startFrom, boolean contained){
        StringBuilder builder = new StringBuilder();
        builder.append(this.baseUrl);
        builder.append(addParameter(PAGE_SIZE, this.resultsPerPage, true));
        if(contained){
            builder.append(addParameter(START_FROM, startFrom,false));
        }
        if(this.requestParameters != null && this.requestParameters.size()>0){
            builder.append(addParameters(requestParameters));
        }
        return builder.toString();
    }

    /**
     * Add All Request Parameters To The URL String Under Processing
     * @param parameters Map<String, String[]> Containing The Request Parameters
     * @return String Result Like "&param1=value1&param2=value2&param2=value2b"
     */
    private String addParameters(Map<String, String[]> parameters){
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, String[]> entry : parameters.entrySet())  {
            String name = entry.getKey();
            if(!name.equals(START_FROM) && !name.equals(PAGE_SIZE)){
                String[] values = entry.getValue();
                for(String value : values){
                    builder.append(addParameter(name, value, false));
                }
            }
        }
        return builder.toString();
    }

    /**
     * Add The Given Name / Values To A URL Parameters
     * @param name Parameter's Name
     * @param value Paramter's Value
     * @param firstParameter boolean Indicating If The Parameter Is The First One After The URI Or Not
     * @return String Result Like "&paramName=paramValue"
     */
    private String addParameter(String name, Object value, boolean firstParameter){
        StringBuilder builder = new StringBuilder()
                                .append(firstParameter ? "?" : "&")
                                .append(name)
                                .append("=")
                                .append(value);
        return builder.toString();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Integer getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }

    public Integer getResultsInTotal() {
        return resultsInTotal;
    }

    public void setResultsInTotal(Integer resultsInTotal) {
        this.resultsInTotal = resultsInTotal;
    }

    public Integer getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(Integer startFrom) {
        this.startFrom = startFrom;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Map<String, String[]> getElements() {
        return elements;
    }

    public void setElements(Map<String, String[]> elements) {
        this.elements = elements;
    }

}
