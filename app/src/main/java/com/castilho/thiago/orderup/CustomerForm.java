package com.castilho.thiago.orderup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.castilho.thiago.orderup.models.Customer;
import com.castilho.thiago.orderup.models.Order;
import com.castilho.thiago.orderup.services.OrderService;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerForm extends AppCompatActivity {

    EditText edt_name, edt_phone, edt_email, edt_address, edt_credit_card;
    Button btn_confirm_order;
    OrderService service;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);


        setTitle("Personal Information:");

        //set views
        InitializeComponents(); //Just like .NET why they don`t make things simple like this.

        //init service
        service = OrderService.getInstance();
        //set validations;
        setValidations();

        if (service.getOrder().getCustomer() != null){
            fillForm(service.getOrder().getCustomer());
        }
    }

    protected void InitializeComponents(){
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_address = (EditText) findViewById(R.id.edt_address);
        edt_credit_card = (EditText) findViewById(R.id.edt_credit_card);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        btn_confirm_order = (Button)findViewById(R.id.btn_confirm_order);
        btn_confirm_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               submitForm();
            }
        });
    }

    private void submitForm() {
        if (awesomeValidation.validate()) {
            service.getOrder().setCustomer(new Customer(
                    edt_name.getText().toString(),
                    edt_email.getText().toString(),
                    edt_phone.getText().toString(),
                    edt_address.getText().toString(),
                    edt_credit_card.getText().toString()
            )
            );

            Intent checkout = new Intent(CustomerForm.this, Checkout.class);
            startActivity(checkout);
        }
    }

    protected void fillForm(Customer customer){
        edt_name.setText(customer.getName());
        edt_email.setText(customer.getEmail());
        edt_phone.setText(customer.getPhone());
        edt_address.setText(customer.getAddress());
        edt_credit_card.setText(customer.getCreditCard());
    }

    protected void setValidations(){

        awesomeValidation.addValidation(this, R.id.edt_name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.edt_email, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.edt_phone, "^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$", R.string.invalid_phone);
        awesomeValidation.addValidation(this,R.id.edt_credit_card, "^((4\\d{3})|(5[1-5]\\d{2}))(-?|\\040?)(\\d{4}(-?|\\040?)){3}|^(3[4,7]\\d{2})(-?|\\040?)\\d{6}(-?|\\040?)\\d{5}\n",R.string.invalid_credit_card);


        edt_credit_card.addTextChangedListener(new TextWatcher() {

            private static final char space = ' ';

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                // Remove spacing char
                if (editable.length() > 0 && (editable.length() % 5) == 0) {
                    final char c = editable.charAt(editable.length() - 1);
                    if (space == c) {
                        editable.delete(editable.length() - 1, editable.length());
                    }
                }
                // Insert char where needed.
                if (editable.length() > 0 && (editable.length() % 5) == 0) {
                    char c = editable.charAt(editable.length() - 1);
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(editable.toString(), String.valueOf(space)).length <= 3) {
                        editable.insert(editable.length() - 1, String.valueOf(space));
                    }
                }

            }
        });
        edt_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int cursorPosition, int before, int count) {
                if (before == 0 && count == 1) {  //Entering values

                    String val = s.toString();
                    String a = "";
                    String b = "";
                    String c = "";
                    if (val != null && val.length() > 0) {
                        val = val.replace("-", "");
                        if (val.length() >= 3) {
                            a = val.substring(0, 3);
                        } else if (val.length() < 3) {
                            a = val.substring(0, val.length());
                        }
                        if (val.length() >= 6) {
                            b = val.substring(3, 6);
                            c = val.substring(6, val.length());
                        } else if (val.length() > 3 && val.length() < 6) {
                            b = val.substring(3, val.length());
                        }
                        StringBuffer stringBuffer = new StringBuffer();
                        if (a != null && a.length() > 0) {
                            stringBuffer.append(a);

                        }
                        if (b != null && b.length() > 0) {
                            stringBuffer.append("-");
                            stringBuffer.append(b);

                        }
                        if (c != null && c.length() > 0) {
                            stringBuffer.append("-");
                            stringBuffer.append(c);
                        }

                        edt_phone.removeTextChangedListener(this);
                        edt_phone.setText(stringBuffer.toString());
                        if (cursorPosition == 3 || cursorPosition == 7) {
                            cursorPosition = cursorPosition + 2;
                        } else {
                            cursorPosition = cursorPosition + 1;
                        }
                        if (cursorPosition <= edt_phone.getText().toString().length()) {
                            edt_phone.setSelection(cursorPosition);
                        } else {
                            edt_phone.setSelection(edt_phone.getText().toString().length());
                        }
                        edt_phone.addTextChangedListener(this);
                    } else {
                        edt_phone.removeTextChangedListener(this);
                        edt_phone.setText("");
                        edt_phone.addTextChangedListener(this);
                    }

                }

                if (before == 1 && count == 0) {  //Deleting values

                    String val = s.toString();
                    String a = "";
                    String b = "";
                    String c = "";

                    if (val != null && val.length() > 0) {
                        val = val.replace("-", "");
                        if (cursorPosition == 3) {
                            val = removeCharAt(val, cursorPosition - 1, s.toString().length() - 1);
                        } else if (cursorPosition == 7) {
                            val = removeCharAt(val, cursorPosition - 2, s.toString().length() - 2);
                        }
                        if (val.length() >= 3) {
                            a = val.substring(0, 3);
                        } else if (val.length() < 3) {
                            a = val.substring(0, val.length());
                        }
                        if (val.length() >= 6) {
                            b = val.substring(3, 6);
                            c = val.substring(6, val.length());
                        } else if (val.length() > 3 && val.length() < 6) {
                            b = val.substring(3, val.length());
                        }
                        StringBuffer stringBuffer = new StringBuffer();
                        if (a != null && a.length() > 0) {
                            stringBuffer.append(a);

                        }
                        if (b != null && b.length() > 0) {
                            stringBuffer.append("-");
                            stringBuffer.append(b);

                        }
                        if (c != null && c.length() > 0) {
                            stringBuffer.append("-");
                            stringBuffer.append(c);
                        }
                        edt_phone.removeTextChangedListener(this);
                        edt_phone.setText(stringBuffer.toString());
                        if (cursorPosition == 3 || cursorPosition == 7) {
                            cursorPosition = cursorPosition - 1;
                        }
                        if (cursorPosition <= edt_phone.getText().toString().length()) {
                            edt_phone.setSelection(cursorPosition);
                        } else {
                            edt_phone.setSelection(edt_phone.getText().toString().length());
                        }
                        edt_phone.addTextChangedListener(this);
                    } else {
                        edt_phone.removeTextChangedListener(this);
                        edt_phone.setText("");
                        edt_phone.addTextChangedListener(this);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            public String removeCharAt(String s, int pos, int length) {

                String value = "";
                if (length > pos) {
                    value = s.substring(pos + 1);
                }
                return s.substring(0, pos) + value;
            }
        });

    }
}
