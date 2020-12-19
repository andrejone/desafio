import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Country } from 'src/app/dto/country';
import { EconomicIndicator } from 'src/app/dto/economic-indicator';
import { CoreService } from 'src/app/services/core.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, AfterViewInit {

  paginator: MatPaginator;
  codigoFormControl = new FormControl('');
  paisFormControl = new FormControl('');
  indicadores : EconomicIndicator[] = [];
  datasource : MatTableDataSource<EconomicIndicator>;
  displayedColumns = ['position', 'data', 'idPais', 'pais', 'countryiso3code', 'observacao'];
  paises: Country[] = [];
  mensagem: string = null;
  showProgress = false;

  @ViewChild(MatPaginator) set matPaginator(mp: MatPaginator) {
    this.paginator = mp;
    this.setDataSourceAttributes();
  }

  setDataSourceAttributes() {
    this.datasource.paginator = this.paginator;
  }

  constructor(
    private coreService: CoreService
  ) { 
    this.datasource = new MatTableDataSource(this.indicadores);
  }

  ngOnInit(): void {
    this.getPaises();
  }

  ngAfterViewInit() {
    // this.datasource.paginator = this.paginator;
  }

  getPaises() {
    this.coreService.listCountries().subscribe(res => {
      this.paises = res;
    })
  }

  pesquisar() {
    this.mensagem = null;
    // let codigo = this.codigoFormControl.value;
    let codigo = this.paisFormControl.value;
    if(codigo == null || codigo == '') {
      // codigo = this.codigoFormControl.value;
      // if(codigo == null || codigo == '') {
        this.mensagem = 'Obrigatório informar o código';
      // }
    } else {
      this.showProgress = true;
      this.coreService.findByCountryCode(codigo.toUpperCase()).subscribe(res => {
        this.showProgress = false;
        if(res == null || res.length == 0) {
          this.mensagem = 'Nenhum registro encontrado';
        }
        this.indicadores = res;
        this.datasource.data = this.indicadores;
        // this.datasource.paginator = this.paginator;
      })
    }
  }

  limpar() {
    this.paisFormControl = new FormControl();
    this.codigoFormControl = new FormControl();
    this.indicadores = [];
    this.datasource = new MatTableDataSource(this.indicadores);
    this.datasource.paginator = this.paginator;
    this.mensagem = null;
    this.showProgress = false;
  }

}
