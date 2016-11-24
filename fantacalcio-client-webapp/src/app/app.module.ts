import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { HeroDetailComponent } from './hero/detail/hero-detail.component';


@NgModule({
    declarations: [AppComponent, HeroDetailComponent],
    imports: [BrowserModule, FormsModule],
    bootstrap: [AppComponent]
})
export class AppModule {}
