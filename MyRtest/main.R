# Title     : TODO
# Objective : TODO
# Created by: kurum
# Created on: 11-06-2021

get_result=GET("http://localhost:2000/api/v1/bankAccountLookup/withBalance/GREATER_THAN/140",add_headers(Authorization = paste("Bearer","" )))
get_result_text<-content(get_result,"text")
get_result_text
get_json_result<-fromJSON(get_result_text,flatten=TRUE)
result_data_frame<-as.data.frame(get_json_result)
modified_result<-select(result_data_frame,CustomerId=accounts.id,Balance=accounts.balance)
p<-ggplot(data=modified_result,aes(x=CustomerId,y=Balance))+
   geom_bar(stat="identity",width=0.5,color="blue",fill="steelblue")+
  geom_text(aes(label=Balance),vjust=1.6,color="white",size=3.5)+
  theme_minimal()
p
