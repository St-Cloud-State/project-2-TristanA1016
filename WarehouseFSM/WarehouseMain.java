public class WarehouseMain {
    public static void main(String[] args) {
        WarehouseContext.instance().start();
        WarehouseManagementSystem wms = new WarehouseManagementSystem();
        wms.setSystemID(1);
        wms.run();
        
    }
}
