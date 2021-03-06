package model;

import DTO.ItemDTO;
import DTO.ReceiptItemsDTO;

import java.util.ArrayList;

public class Sale {
    ArrayList<ReceiptItemsDTO> receiptItemsDTOS = new ArrayList<>();

    /**
     * this function is responsible for adding the current item to the receipt item list, it handle if there is no item is not on the list,
     * if the current item is not on the list and if the current item is already in the list.
     *
     *
     * @param itemInfo
     * @return it returns the list of the items and the info of them.
     */
    public ArrayList<ReceiptItemsDTO> addItemsToListAndCalculatePrice(ItemDTO itemInfo){
        ReceiptItemsDTO receiptItemsDTO;
        if(receiptItemsDTOS.size() != 0) {
            if(!(checkIfItemAlreadyInReceipt(itemInfo,receiptItemsDTOS) == -1)){
                int index = checkIfItemAlreadyInReceipt(itemInfo,receiptItemsDTOS);
                receiptItemsDTO = receiptItemsDTOS.get(index);
                receiptItemsDTO.setQuantity(receiptItemsDTO.getQuantity() + 1);
                receiptItemsDTO.setPrice(getPriceForMultipleItems(itemInfo, receiptItemsDTO.getQuantity()));
                System.out.println(receiptItemDtoToString(receiptItemsDTO));
                return receiptItemsDTOS;
            }
            else{

                receiptItemsDTO = new ReceiptItemsDTO(1, itemInfo);
                receiptItemsDTO.setItemDTO(itemInfo);
                receiptItemsDTOS.add(receiptItemsDTO);
                receiptItemsDTO.setPrice(getPriceForMultipleItems(itemInfo, receiptItemsDTO.getQuantity()));
                System.out.println(receiptItemDtoToString(receiptItemsDTO));
                return receiptItemsDTOS;
            }
        }else{
            receiptItemsDTO = new ReceiptItemsDTO(1, itemInfo);
            receiptItemsDTO.setItemDTO(itemInfo);
            receiptItemsDTOS.add(receiptItemsDTO);
            receiptItemsDTO.setPrice(getPriceForMultipleItems(itemInfo, receiptItemsDTO.getQuantity()));
            System.out.println(receiptItemDtoToString(receiptItemsDTO));
            return receiptItemsDTOS;
        }
    }

    public ArrayList<ReceiptItemsDTO> returnReceiptItemsDTO(){
        return this.receiptItemsDTOS;
    }

    public void resetReceiptItemsDTO() {
        this.receiptItemsDTOS = new ArrayList<>();
    }

    /**
     * this function checks if the item is already in the receipt items list.
     * @param itemDTO   itemDTO which contains the info of the item.
     * @param receiptItemsDTOS  this parameter contains the list of the items.
     * @return either the item id or -1, if the item is not in the list it returns -1.
     */
    public int checkIfItemAlreadyInReceipt(ItemDTO itemDTO, ArrayList<ReceiptItemsDTO> receiptItemsDTOS){
        for(int i = 0; i < receiptItemsDTOS.size(); i++){
            int itemInLoopsID = receiptItemsDTOS.get(i).getItemDTO().getId();
            int currentItemId = itemDTO.getId();
            if(itemInLoopsID == currentItemId) {
                return i;
            }
            else{
            }
        }
        return -1;
    }


    /**
     * this function calculate the total price of many items with the same id.
     * @param itemDTO   contains the info of the item
     * @param quantity  the quantity of this item in the list
     * @return  returns the price of the number of items with the same id.
     */
    public int getPriceForMultipleItems(ItemDTO itemDTO, int quantity){

        return itemDTO.getProductPrice() * quantity;

    }


    public String receiptItemDtoToString(ReceiptItemsDTO receiptItemsDTO){
        StringBuilder sb = new StringBuilder();
        sb.append(receiptItemsDTO.getQuantity());
        sb.append("---> ");
        sb.append(itemDtoToString(receiptItemsDTO.getItemDTO()));
        sb.append("  ");
        sb.append(receiptItemsDTO.getPrice());
        /**/
        sb.append("\n");
        String str = sb.toString();
        return str;

    }

    public String itemDtoToString(ItemDTO itemDTO){
        StringBuilder sb = new StringBuilder();
        sb.append(itemDTO.getId());
        sb.append(" ");
        sb.append(itemDTO.getProductPrice());
        sb.append(" ");
        sb.append(itemDTO.getProductDescription());
        sb.append(" ");
        sb.append(itemDTO.getVAT());

        String str = sb.toString();
        return str;
    }

}
