<template>  

  <v-app id="page">


    <v-app-bar app color="pink" dark >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      
      <v-toolbar-title>ระบบห้องสมุด</v-toolbar-title>
        <v-toolbar-items>
         <v-divider inset vertical></v-divider>
         
      </v-toolbar-items>

        <div class="flex-grow-1"></div>

      <v-toolbar-items>
        <v-btn text to="/home">Home</v-btn> <v-divider inset vertical></v-divider>
         <v-divider inset vertical></v-divider>
         <v-btn text to="/Member">ลงทะเบียนสมาชิก</v-btn> <v-divider inset vertical></v-divider>
        <v-btn text to="/AddBook">จัดเก็บรายชื่อหนังสือ</v-btn> <v-divider inset vertical></v-divider>
        <v-btn text to="/addVideo">จัดเก็บวีดีโอ</v-btn> <v-divider inset vertical></v-divider>
        <v-btn text to="/borrow">ยืมหนังสือ</v-btn> <v-divider inset vertical></v-divider>
        <v-btn text to="/remand">คืนหนังสือ</v-btn> <v-divider inset vertical></v-divider>
        <v-btn text to="/viewRateUP">แสดงการให้คะแนนการใช้งาน</v-btn> <v-divider inset vertical></v-divider>
       

      </v-toolbar-items>
    </v-app-bar>



  

  </v-app>  
</template>

<style>
#page {
  background-image: url("https://dcassetcdn.com/design_img/2495449/83570/83570_13222851_2495449_a5ee7223_image.png");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
</style>


