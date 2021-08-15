package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();

		// print headers
		output.append("======Printing Orders======\n");

		// print date, bill no, customer name
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());

		// prints lineItems
		double totalSaleTax = 0d;
		double totalAmount = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			receipt(output, lineItem);

			// calculate sales tax @ rate of 10%
			totalSaleTax+= lineItem.totalAmount() * .10;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount += lineItem.totalAmount();
		}

		// prints the state tax
		output.append("Sales Tax").append('\t').append(totalSaleTax);

        // print total amount
		output.append("Total Amount").append('\t').append(totalAmount+totalSaleTax);
		return output.toString();
	}

	private void receipt(StringBuilder output, LineItem lineItem) {
		output.append(lineItem.getDescription());
		output.append('\t');
		output.append(lineItem.getPrice());
		output.append('\t');
		output.append(lineItem.getQuantity());
		output.append('\t');
		output.append(lineItem.totalAmount());
		output.append('\n');
	}
}