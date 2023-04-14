<template>
	<div>
        <el-container class="body" direction="vertical">
			<el-header style="height:220px;">
				<div  >
					<router-link class="title" :class="{red:!colorFlag,white:colorFlag}"  
						to="/chart"><span @click="go(false)">宝可梦图鉴</span>
					</router-link>
				</div>
				
				<div >
					<router-link class="title"  :class="{red:colorFlag,white:!colorFlag}"  
						to="/manage"><span @click="go(true)">宝可梦管理</span>
					</router-link>
				</div>
			</el-header> 
			<el-main><router-view /></el-main>
		</el-container>
    </div>
</template>

<script>

	export default {
		name: 'HomeView',
		components: {

		},
		data() {
			return {
				chart:false,
				manage:true,
				colorFlag:true,
			};
		},
		methods: {
			go(flag){
				this.colorFlag = flag
			}
		},
	}
</script>

<style>
	.red{
		color:red;
	}
	.white{
		color:whitesmoke;
	}
	.body{
        margin: 0px;
        padding: 0px;
        width: 100vw;
        height: 100vh;
        background-color: whitesmoke;
        display: flex;
    }
    
    .el-header{
        display: flex;
        /* margin: 0 200px; */
        justify-content: space-around;
        align-items: center;
        padding: 10px;
		background:url("../assets/head.png") no-repeat;
        background-size:cover;
        background-attachment:scroll;
	}
	.title{
		
		text-decoration: none;
	}
    
</style>
