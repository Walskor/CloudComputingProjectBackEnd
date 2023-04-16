# CloudComputingProjectBackEnd
## 1. Open RDS service on AWS
The settings I used are just from the AWS02 Lab

Step 1: Select AWS Service

Step 2: Create DB Instance

Step 3: Add inbound rule to DB Security Group 

Step 4: Verify Creation 

But please
pay attention here, so not forget to reconfigure the 
routine table to make sure it can connect with the EC2
instances.
## 2. Open two EC2 instances
The two EC2 instances have the same configuration as the
AWS02 Lab, so you can open one first, transfer the JAR files, generate AMI and create
another EC2 instance. Here we need to make sure the two
instances should be in two different subnets, which will be used in the 
Load Balancer.
## 3. Open Load Balancer
Still, the steps are the same as the AWS02 Lab

• Select AWS Service

• Select Load Balancer Type

• Basic Configuration

• Security Group

• Configure Health Check

• Add EC2 instances

• Verify successful creation

• Add access to EC2 security group

But here
we need to change the Health Check into Ping Port 8080 and 
Ping Path /hello/world, which will access the test page of 
our program.
## 4. Generating JAR files needed
We need to generate the JAR files needed to implement 
on the cloud. Because we develop the project in Idea so 
here we use Idea to describe the whole process. 
First build the whole project, which the logo is a green 
hammer. 

And then click File->Project Structure->Artifacts
->'+' at the left upper->Jar->From modules with dependencies.
Here module and class only have one choice, so just choose it.
For JAR files from libraries we choose copy to 
the output directory and link via manifest, which will generate
different JAR files for the code itself and the dependencies.
Then we can just click OK, apply, OK in sequence.
## 5. Implementation
We need to transfer all the generated JAR files to the 
EC2 instances. And please make sure the JAR files are in the 
same directory on the EC2. Run the project with command
"java -jar project.jar", then you can see the project
running is successful.
## 6. Access the website with the DNS name of the balance loader
